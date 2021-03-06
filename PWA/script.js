//const pomodoroTimer = document.querySelector("#pomodoro-timer");
const startButton = document.querySelector("#pomodoro-start");
//const pauseButton = document.querySelector("#pomodoro-pause");
const stopButton = document.querySelector("#pomodoro-stop");
let currentTaskLabel = document.querySelector("#pomodoro-clock-task");
let updatedWorkSessionDuration;
let updatedBreakSessionDuration;
let workDurationInput = document.querySelector("#input-work-duration");
let breakDurationInput = document.querySelector("#input-break-duration");
workDurationInput.value = "25";
breakDurationInput.value = "5";
let isClockRunning = false;
let type = "Work";
let timeSpentInCurrentSession = 0;
let isClockStopped = true

// in seconds = 25 mins
let workSessionDuration = 1500;
let currentTimeLeftInSession = 1500;
// in seconds = 5 mins;
let breakSessionDuration = 300;

// UPDATE WORK TIME
workDurationInput.addEventListener('input', () => {
  updatedWorkSessionDuration = minuteToSeconds(workDurationInput.value)
})
// UPDATE PAUSE TIME
breakDurationInput.addEventListener('input', () => {
  updatedBreakSessionDuration = minuteToSeconds(breakDurationInput.value)
})

const minuteToSeconds = (mins) => {
  return mins * 60
}

// START
startButton.addEventListener("click", () => {
  toggleClock();
});
// PAUSE
// pauseButton.addEventListener("click", () => {
//   toggleClock();
// });
// STOP
stopButton.addEventListener("click", () => {
  toggleClock(true);
});



const stopClock = () => {
  setUpdatedTimers()
  displaySessionLog(type)
  clearInterval(clockTimer)
  isClockStopped = true
  isClockRunning = false
  currentTimeLeftInSession = workSessionDuration
  displayCurrentTimeLeftInSession()
  type = 'Work'
  timeSpentInCurrentSession = 0
}

const setUpdatedTimers = () => {
  if (type === 'Work') {
    currentTimeLeftInSession = updatedWorkSessionDuration
      ? updatedWorkSessionDuration
      : workSessionDuration
    workSessionDuration = currentTimeLeftInSession
  } else {
    currentTimeLeftInSession = updatedBreakSessionDuration
      ? updatedBreakSessionDuration
      : breakSessionDuration
    breakSessionDuration = currentTimeLeftInSession
  }
}

const toggleClock = (reset) => {
  togglePlayPauseIcon(reset)
  if (reset) {
    stopClock()
  } else {
    console.log(isClockStopped)
    if (isClockStopped) {
      setUpdatedTimers()
      isClockStopped = false
    }
    if (isClockRunning === true) {
      // pause
      clearInterval(clockTimer)
      // update icon to the play one
      // set the vale of the button to start or pause
      isClockRunning = false
    } else {
      // start
      clockTimer = setInterval(() => {
        stepDown()
        displayCurrentTimeLeftInSession()
      }, 1000)
      isClockRunning = true
    }
    // new
    showStopIcon()
  }
}

const displaySessionLog = (type) => {
  const sessionsList = document.querySelector("#pomodoro-sessions");
  // append li to it
  const li = document.createElement("li");
  if (type === "Work") {
    sessionLabel = currentTaskLabel.value ? currentTaskLabel.value : "Work";
    workSessionLabel = sessionLabel;
  } else {
    sessionLabel = "Break";
  }
  let elapsedTime = parseInt(timeSpentInCurrentSession / 60);
  elapsedTime = elapsedTime > 0 ? elapsedTime : "< 1";
  const text = document.createTextNode(`${sessionLabel} : ${elapsedTime} min`);
  li.appendChild(text);
  sessionsList.appendChild(li);
};

const stepDown = () => {
  
  if (currentTimeLeftInSession > 0) {
    // decrease time left / increase time spent
    currentTimeLeftInSession--;
    timeSpentInCurrentSession++;
  } else if (currentTimeLeftInSession === 0) {
    timeSpentInCurrentSession = 0;
    // Timer is over -> if work switch to break, viceversa
    if (type === "Work") {
      currentTimeLeftInSession = breakSessionDuration;
      displaySessionLog("Work");
      type = "Break";
      // new
      currentTaskLabel.value = "Break";
      currentTaskLabel.disabled = true;
    } else {
      currentTimeLeftInSession = workSessionDuration;
      type = "Work";
      // new
      if (currentTaskLabel.value === "Break") {
        currentTaskLabel.value = workSessionLabel;
      }
      currentTaskLabel.disabled = false;
      displaySessionLog("Break");
    }
    
  }
clockTimer = setInterval(() => {
  //stepDown()
  displayCurrentTimeLeftInSession()
  progressBar.set(calculateSessionProgress())
}, 100)
  
};

const displayCurrentTimeLeftInSession = () => {
  const secondsLeft = currentTimeLeftInSession;
  let result = "";
  const seconds = secondsLeft % 60;
  const minutes = parseInt(secondsLeft / 60) % 60;
  let hours = parseInt(secondsLeft / 3600);
  // add leading zeroes if it's less than 10
  function addLeadingZeroes(time) {
    return time < 10 ? `0${time}` : time;
  }
  if (hours > 0) result += `${hours}:`;
  result += `${addLeadingZeroes(minutes)}:${addLeadingZeroes(seconds)}`;
  progressBar.text.innerText = result.toString()
};

const progressBar = new ProgressBar.Circle('#pomodoro-timer', {
  strokeWidth: 5,
  text: {
    value: '25:00',
  },
  trailColor: '#f4f4f4',
});

const calculateSessionProgress = () => {
  // calculate the completion rate of this session
  const sessionDuration =
    type === 'Work' ? workSessionDuration : breakSessionDuration
  return (timeSpentInCurrentSession / sessionDuration) ; 
}
const togglePlayPauseIcon = (reset) => {
  const playIcon = document.querySelector('#play-icon')
  const pauseIcon = document.querySelector('#pause-icon')
  if (reset) {
    // when resetting -> always revert to play icon
    if (playIcon.classList.contains('hidden')) {
      playIcon.classList.remove('hidden')
    }
    if (!pauseIcon.classList.contains('hidden')) {
      pauseIcon.classList.add('hidden')
    }
  } else {
    playIcon.classList.toggle('hidden')
    pauseIcon.classList.toggle('hidden')
  }
}

const showStopIcon = () => {
  const stopButton = document.querySelector('#pomodoro-stop')
  stopButton.classList.remove('hidden')
}


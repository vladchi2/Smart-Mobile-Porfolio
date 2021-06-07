const musicContainer = document.getElementById('music-container');
const playBtn = document.getElementById('play');
const prevBtn = document.getElementById('prev');
const nextBtn = document.getElementById('next');

const audio = document.getElementById('audio');
const progress = document.getElementById('progress');
const progressContainer = document.getElementById('progress-container');
const title = document.getElementById('title');
const cover = document.getElementById('cover');
const currTime = document.querySelector('#currTime');
const durTime = document.querySelector('#durTime');
let contor = 0;
let contor2 = 0;

const songs = ['hey', 'summer', 'ukulele', 'paradis'];

let songIndex = 2;

loadSong(songs[songIndex]);

// Update song data
function loadSong(song) {
  title.innerText = song;
  audio.src = `music/${song}.mp3`;
  cover.src = `images/${song}.jpg`;
}

// Play the song
function playSong() {
  musicContainer.classList.add('play');
  playBtn.querySelector('i.fas').classList.remove('fa-play');
  playBtn.querySelector('i.fas').classList.add('fa-pause');

  audio.play();
}

// Pause the song
function pauseSong() {
  musicContainer.classList.remove('play');
  playBtn.querySelector('i.fas').classList.add('fa-play');
  playBtn.querySelector('i.fas').classList.remove('fa-pause');

  audio.pause();
}

// Play the Previous song
function prevSong() {
  songIndex--;

  if (songIndex < 0) {
    songIndex = songs.length - 1;
  }

  loadSong(songs[songIndex]);

  playSong();
}

// play the Next song
function nextSong() {
  songIndex++;

  if (songIndex > songs.length - 1) {
    songIndex = 0;
  }

  loadSong(songs[songIndex]);

  playSong();
}

// Updating the progress bar
function updateProgress(e) {
  const { duration, currentTime } = e.srcElement;
  const progressPercent = (currentTime / duration) * 100;
  progress.style.width = `${progressPercent}%`;
}

// Set the progress bar
function setProgress(e) {
  const width = this.clientWidth;
  const clickX = e.offsetX;
  const duration = audio.duration;

  audio.currentTime = (clickX / width) * duration;
}

//get duration & currentTime for Time of song
// function DurTime (e) {
// 	const {duration,currentTime} = e.srcElement;
// 	var sec;
// 	var sec_d;

// 	// define minutes currentTime
// 	let min = (currentTime==null)? 0:
// 	 Math.floor(currentTime/60);
// 	 min = min <10 ? '0'+min:min;

// 	// define seconds currentTime
// 	function get_sec (x) {
// 		if(Math.floor(x) >= 60){
			
// 			for (var i = 1; i<=60; i++){
// 				if(Math.floor(x)>=(60*i) && Math.floor(x)<(60*(i+1))) {
// 					sec = Math.floor(x) - (60*i);
// 					sec = sec <10 ? '0'+sec:sec;
// 				}
// 			}
// 		}else{
// 		 	sec = Math.floor(x);
// 		 	sec = sec <10 ? '0'+sec:sec;
// 		 }
// 	} 

// 	get_sec (currentTime,sec);

// 	// change currentTime DOM
// 	currTime.innerHTML = min +':'+ sec;

// 	// define minutes duration
// 	let min_d = (isNaN(duration) === true)? '0':
// 		Math.floor(duration/60);
// 	 min_d = min_d <10 ? '0'+min_d:min_d;


// 	 function get_sec_d (x) {
// 		if(Math.floor(x) >= 60){
			
// 			for (var i = 1; i<=60; i++){
// 				if(Math.floor(x)>=(60*i) && Math.floor(x)<(60*(i+1))) {
// 					sec_d = Math.floor(x) - (60*i);
// 					sec_d = sec_d <10 ? '0'+sec_d:sec_d;
// 				}
// 			}
// 		}else{
// 		 	sec_d = (isNaN(duration) === true)? '0':
// 		 	Math.floor(x);
// 		 	sec_d = sec_d <10 ? '0'+sec_d:sec_d;
// 		 }
// 	} 

// 	// define seconds duration
	
// 	get_sec_d (duration);

// 	// change duration DOM
// 	durTime.innerHTML = min_d +':'+ sec_d;
		
// };

playBtn.addEventListener('click', () => {
  const isPlaying = musicContainer.classList.contains('play');

  if (isPlaying) {
    pauseSong();
  } else {
    playSong();
  }
});

// ChangeSong
prevBtn.addEventListener('click', prevSong);
nextBtn.addEventListener('click', nextSong);

// Time/songUpdate
audio.addEventListener('timeupdate', updateProgress);

// progress bar
progressContainer.addEventListener('click', setProgress);

// when song ends
audio.addEventListener('ended', nextSong);


function Appear() { 
  if (contor == 0){
    document.getElementById("mainFrame").style.display="block";
    contor++; 
    document.getElementById("time").style.marginLeft = "-10px";
    document.getElementById("time").style.marginRight = "5px";
  }
  else{
    document.getElementById("mainFrame").style.display="none";
    document.getElementById("time").style.marginLeft = "150px";
    contor--; 
  }
	
}

function AppearPlus()
{
 if (contor2 == 0){
   document.getElementById("lights").style.display = "block";
   contor2++;
 }
 else{
   contor2--;
   document.getElementById("lights").style.display = none;
 }
}

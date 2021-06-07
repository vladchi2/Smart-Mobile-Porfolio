import axios from 'axios';


const URL = 'https://community-open-weather-map.p.rapidapi.com/find';
const API_KEY = 'f33a484cf794d08d0148764789aaba32';

export const fetchWeather = async (query) => {
    const { data } = await axios.get(URL, {
        params: {
          q: query,
          units: 'metric',
          APPID: API_KEY,
        }
        
    });

    return data;
}

  
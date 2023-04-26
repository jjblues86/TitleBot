import axios from 'axios'

const TITLE_BOT_BASE_URL = "http://localhost:8080/api/title";

const TITLES_URLS = "titles"

class TitleBot {

    getAllTitles() {
        return axios.get(TITLE_BOT_BASE_URL + "/" + TITLES_URLS);
}

}

export default new TitleBot();
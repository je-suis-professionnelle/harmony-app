import axios from 'axios';

const API_URL = 'http://localhost:8080/api/users/auth/';

class AuthService {
    login(user) {
        return axios
            .post(API_URL + 'signin', {
                pseudo: user.pseudo,
                mdp: user.mdp
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return axios.post(API_URL + 'signup', {
            pseudo: user.pseudo,
            mdp: user.mdp
        });
    }
}

export default new AuthService();
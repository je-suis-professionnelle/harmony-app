import axios from 'axios';
import Groupe from '../models/group.js';

const state = {
  groupes: [],
};

const mutations = {
  setGroupes(state, groupes) {
    state.groupes = groupes.map(g => new Groupe(g.id, g.idOwner, g.titre));
  },
};

const actions = {
  fetchGroupes({ commit, rootState }) {

    const token = rootState.auth.user.accessToken;
    const headers = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };

    axios.get('http://localhost:8080/groups', headers)
      .then(response => {
        commit('setGroupes', response.data);
      })
      .catch(error => {
        console.error('Erreur lors de la récupération des groupes :', error);
      });
  },
};

const getters = {
  getGroupeById: state => id => {
    return state.groupes.find(groupe => groupe.id === id);
  },
};

export const group = {
  state,
  mutations,
  actions,
  getters,
};
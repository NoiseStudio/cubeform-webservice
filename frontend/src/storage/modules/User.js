const state = () => ({
   isLogged: false,
   token: null,
   id: null,
});

// getter(state, getters, rootState)
const getters = {
    isLogged(state) {
        return state.isLogged;
    },
    userToken(state, getters) {
        return getters.isLogged ? state.token : null;
    },
    userId(state, getters){
        return getters.isLogged ? state.id : null;
    },
    userData(state, getters) {
        if(getters.isLogged)
            return {
                isLogged: true,
                token: getters.userToken,
                id: getters.userId,
            }

        return { isLogged: false };
    }

}

const mutations = {
    setUserId(state, newId) {
        state.id = newId;
    },
    setUserToken(state, newToken) {
        state.token = newToken;
    },
    setUserLogged(state, isLogged) {
        state.isLogged = isLogged;
    }
}

const actions = {
    logout({ commit }){
        commit('setUserId', null);
        commit('setUserToken', null);
        commit('setUserLogged', false);
    },
    login({ commit }, {id, token}) {
        commit('setUserId', id);
        commit('setUserToken', token);
        commit('setUserLogged', true);
    }
}


export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
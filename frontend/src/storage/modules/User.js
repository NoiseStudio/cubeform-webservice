const lsKey = "__vuex_user";

const save = (obj) => {
    localStorage.setItem(lsKey, JSON.stringify(obj));
}
const load = (oldState) => {
    let dataStr = localStorage.getItem(lsKey);
    let data;
    try {
        data = JSON.parse(dataStr) || null;
    }
    catch (err){
        data = null;
    }

    if(data === null){
        console.log("no User state in local storage");
        return;
    }

    oldState.isLogged = data.isLogged || false;
    oldState.token = data.token || null;
    oldState.id = data.id || null;

    console.log("Local storage User state loaded");
}

const state = () => {
    let stateObj = {
        isLogged: false,
        token: null,
        id: null
    }
    load(stateObj);
    return stateObj;
};

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
        save(state);
    },
    setUserToken(state, newToken) {
        state.token = newToken;
        save(state);
    },
    setUserLogged(state, isLogged) {
        state.isLogged = isLogged;
        save(state);
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
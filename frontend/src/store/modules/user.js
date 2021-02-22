import { ServiceFactory } from '../../services/ServiceFactory';
const authService = ServiceFactory.get('auth');


const state = {
    user: null,
    search: null
};

const getters = {
    user: state => state.user,
    search: state => state.search
};

const actions = {
    // async getTodos({ commit }) {
    //     let response;
    //     try {
    //         response = await authService.test();
    //         await commit('setTodos', response.data);
    //     } catch (e) { }
    // },
};

const mutations = {
    setUser: (state, data) => {
        state.user = data;
    },
    setSearch: (state, data) => {
        state.search = data;
    }
};

export default {
    state,
    getters,
    actions,
    mutations
}
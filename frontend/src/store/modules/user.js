import { ServiceFactory } from '../../services/ServiceFactory';
const authService = ServiceFactory.get('auth');


const state = {
    user: null
};

const getters = {
    user: state => state.user
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
    }
};

export default {
    state,
    getters,
    actions,
    mutations
}
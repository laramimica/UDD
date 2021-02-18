import AuthService from './modules/AuthService';

const authService = new AuthService();

const services = {
    auth: authService,
};

export const ServiceFactory = {
    get: name => services[name]
};
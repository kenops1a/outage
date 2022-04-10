import store from "@/store/store";

export function success(message) {
    store.commit('$_successBarSwitch')
    store.commit('$_setSuccessBarMessage', message)
}

export function error(message) {
    store.commit('$_errorBarSwitch')
    store.commit('$_setErrorBarMessage', message)
}

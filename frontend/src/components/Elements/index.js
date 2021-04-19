import * as components from './Components/';

let Elements = Vue => {
    for(let comp in components) {
        Vue.component(components[comp].name, components[comp]);
    }
}

export default Elements;
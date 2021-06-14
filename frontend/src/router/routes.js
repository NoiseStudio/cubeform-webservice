import HelloWorld from "@/components/HelloWorld";
import Servers from "@/components/Servers";
import ServerRegistrationForm from "@/components/ServerRegistrationForm";
import NewsPage from "@/components/NewsPage"
import LoginPage from "@/components/LoginPage";
import RegisterPage from "@/components/RegisterPage";
import Logout from "@/components/Logout";
import MyServers from "../components/MyServers";

export default [
    {
        path: "/",
        component: HelloWorld,
        meta: {
            allowWithoutLogin: true
        }
    },
    {
        path: '/login',
        component: LoginPage,
        meta: {
            blockLogged: true
        }
    },
    {
        path: '/logout',
        component: Logout,
        meta: {
            allowWithoutLogin: true // why?
        }
    },
    {
        path: '/registration',
        component: RegisterPage,
        meta: {
            blockLogged: true
        }
    },
    {
        path: "/servers",
        component: Servers
    },
    {
        path: "/registerServer",
        component: ServerRegistrationForm
    },
    {
        path: "/news",
        component: NewsPage
    },
    {
        path: "/myServers",
        component: MyServers
    },
];
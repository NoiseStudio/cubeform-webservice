import HelloWorld from "@/components/HelloWorld";
import Test from "@/components/Test";
import Servers from "@/components/Servers";
import ServerRegistrationForm from "@/components/ServerRegistrationForm";
import NewsPage from "@/components/NewsPage"



export default [
    {
        path: "/",
        component: HelloWorld
    },
    {
        path: '/test',
        component: Test,
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
    }
];
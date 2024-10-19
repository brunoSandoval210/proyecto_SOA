import { Routes } from "@angular/router";

export default [
    {
        path:'',
        loadComponent: () => import('./login.component-2'),
    },
    {
        path:'login',
        loadComponent: () => import('./login.component-2'),
    },
] as Routes;
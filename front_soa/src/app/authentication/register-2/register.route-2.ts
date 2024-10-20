import { Routes } from "@angular/router";

export default [
    {
        path:'register-2',
        loadComponent: () => import('./register.component-2').then(m => m.RegisterComponent2)
    }
] as Routes;
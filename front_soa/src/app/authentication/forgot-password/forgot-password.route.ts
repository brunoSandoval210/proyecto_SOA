import { Routes } from "@angular/router";

export default [
    {
        path:'forgot-password',
        loadComponent: () => import('./forgot-password.component').then(m => m.ForgotPasswordComponent)
    }
] as Routes;
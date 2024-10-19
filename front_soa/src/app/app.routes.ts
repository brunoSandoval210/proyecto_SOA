import { Routes } from '@angular/router';
import { AuthGuard } from './authentication/auth/auth.guard';
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';

export const routes: Routes = [
    // {
    //     path:'',
    //     loadChildren:()=>import('./authentication/login-shell/login.route')
    // },
    // {
    //     path:'login/oauth2/code/google',
    //     loadChildren: ()=>import('./authentication/login-shell/login.route').then(m => m.default),
    // },
    {
        path:'login',
        loadChildren: ()=> import('./authentication/login-2/login.route-2')
    },
    {
        path:'dashboard',
        loadChildren: ()=>import('./features/dashboard-shell/dashboard.route')
        //canActivate: [AuthGuard]
    },
    {
        path:'register',
        loadComponent:()=>import('./authentication/register/register.component').then(m=>m.RegisterComponent)
    },
    {
        path:'forgot-password',
        loadComponent:()=>import('./authentication/forgot-password/forgot-password.component').then(m=>ForgotPasswordComponent)

    },
    { 
        path: '**',
        redirectTo: 'login'
    }

];

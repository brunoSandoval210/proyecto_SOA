import { Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';
import { Login2Component } from './authentication/login2/login2.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';

export const routes: Routes = [
    //****************************Login *****************************/
    {path: '', redirectTo: '/login', pathMatch: 'full'},
    {path:'login', component:Login2Component},
    {path:'register', component: RegisterComponent},
    {path:'forgot-password', component:ForgotPasswordComponent},

    //****************************Dashboard *****************************/
    {path:'dashboard', component:DashboardComponent},
    // {
    //     path:'',
    //     pathMatch:'full',
    //     redirectTo:'auth/login'
    // },
    // {
    //     path: 'auth',
    //     loadChildren: () => import('./authentication/auth.routes').then(m => m.authRoutes),
    // },
    // {
    //     path: 'features',
    //     loadChildren: () => import('./features/features.routes').then(m => m.featuresRoutes),
    // },
    // {
    //     path:'home',
    //     component:HomeComponent
    // }
];

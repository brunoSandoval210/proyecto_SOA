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

];

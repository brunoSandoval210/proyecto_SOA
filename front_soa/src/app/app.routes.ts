import { Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';
import { Login2Component } from './authentication/login2/login2.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { RegisterComponent } from './authentication/register/register.component';
import { ForgotPasswordComponent } from './authentication/forgot-password/forgot-password.component';
import { AuthGuard } from './core/services/auth.guard';
import { AuthRedirectGuard } from './core/services/auth-redirect.guard';

export const routes: Routes = [
    //****************************Login *****************************/
    {path: '', redirectTo: '/login', pathMatch: 'full'},
    {path:'login', component:Login2Component, canActivate: [AuthRedirectGuard]},
    {path:'register', component: RegisterComponent},
    {path:'forgot-password', component:ForgotPasswordComponent},

    //****************************Dashboard *****************************/
    {path:'dashboard', component:DashboardComponent, canActivate: [AuthGuard]},

];

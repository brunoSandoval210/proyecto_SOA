import { Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { RegisterComponent } from './componentes/register/register.component';
import { HomeComponent } from './componentes/home/home.component';
import LoginComponent2 from './authentication/login-2/login.component-2';
import { RegisterComponent2 } from './authentication/register-2/register.component-2';

export const routes: Routes = [
    {
        path:'',
        pathMatch:'full',
        redirectTo:'login'
    },
    {
        path:'login',
        component:LoginComponent
    },
    {
        path:'login2',
        component:LoginComponent2
    },
    {
        path:'register2',
        component:RegisterComponent2
    },
    {
        path:'register',
        component:RegisterComponent
    },
    {
        path:'home',
        component:HomeComponent
    }



];

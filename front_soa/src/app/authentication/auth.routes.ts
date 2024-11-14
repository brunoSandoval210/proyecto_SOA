import { Routes } from '@angular/router';

export const authRoutes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'login-2',
    loadComponent: () => import('./login-2/login.component-2').then(m => m.default),
  },
  {
    path: 'login3',
    loadComponent: () => import('./login2/login2.component').then(m => m.Login2Component),
  },
  {
    path: 'forgot-password',
    loadComponent: () => import('./forgot-password/forgot-password.component').then(m => m.ForgotPasswordComponent),
  },
  {
    path: 'register',
    loadComponent: () => import('./register/register.component').then(m => m.RegisterComponent),
  }
];

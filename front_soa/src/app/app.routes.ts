import { Routes } from '@angular/router';
import { HomeComponent } from './layout/home/home.component';

export const routes: Routes = [
    {
        path:'',
        pathMatch:'full',
        redirectTo:'auth/login3'
    },
    {
        path: 'auth',
        loadChildren: () => import('./authentication/auth.routes').then(m => m.authRoutes),
    },
    {
        path: 'features',
        loadChildren: () => import('./features/features.routes').then(m => m.featuresRoutes),
    },
    {
        path:'home',
        component:HomeComponent
    }
];

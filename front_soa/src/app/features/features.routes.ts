import { Routes } from '@angular/router';

export const featuresRoutes: Routes = [
  { 
    path: 'dashboard',
    loadComponent: () => import('./dashboard/dashboard.component').then(m => m.DashboardComponent),
  },
  { 
    path: 'list', 
    loadComponent: () => import('./list/list.component').then(m => m.ListComponent), 
  },
  { 
    path: 'settings', 
    loadComponent: () => import('./settings/settings.component').then(m => m.SettingsComponent), 
  },
  { 
    path: 'task', 
    loadComponent: () => import('./task/task.component').then(m => m.TaskComponent), 
  },

];
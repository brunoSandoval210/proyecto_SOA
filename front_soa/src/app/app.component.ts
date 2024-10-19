import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import LoginComponent from './authentication/login-2/login.component-2';
import DashboardComponent from './features/dashboard/dashboard.component';
import { RegisterComponent } from './authentication/register/register.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,LoginComponent,DashboardComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'front_soa';
}

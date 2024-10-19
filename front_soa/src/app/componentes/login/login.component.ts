import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule,ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required], // Cambia "usename" a "username"
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      alert('Por favor, ingrese usuario y contraseña');
      return;
    }
    const { username, password } = this.loginForm.value; // Desestructuración para obtener valores
    this.authService.login({ username, password }).subscribe({
      next: (response: any) => {
        this.authService.setToken(response.token);
        this.router.navigate(['/home']); 
      },
      error: (err) => {
        alert('Error en el inicio de sesión. Verifique sus credenciales.');
      }
    });
  }
}

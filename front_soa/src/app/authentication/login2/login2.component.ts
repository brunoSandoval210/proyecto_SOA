import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';

@Component({
  selector: 'app-login2',
  standalone: true,
  imports: [NgIf, ForgotPasswordComponent, RouterModule, ReactiveFormsModule],
  templateUrl: './login2.component.html',
  styleUrl: './login2.component.scss'
})
export class Login2Component {

  passwordFieldType: string = 'password';
  loginForm2: FormGroup;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.loginForm2 = this.formBuilder.group({
      username: ['', Validators.required], // Cambia "usename" a "username"
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm2.invalid) {
      alert('Por favor, ingrese usuario y contraseña');
      return;
    }
    const { username, password } = this.loginForm2.value; // Desestructuración para obtener valores
    this.authService.login({ username, password }).subscribe({
      next: (response: any) => {
        this.authService.setToken(response.token);
        this.router.navigate(['./features/dashboard']);
      },
      error: (err) => {
        alert('Error en el inicio de sesión. Verifique sus credenciales.');
      }
    });
  }

  togglePasswordVisibility() {
    this.passwordFieldType = (this.passwordFieldType === 'password') ? 'text' : 'password';
  }

  registerFromLogin() {
    this.router.navigate(['/register']);
  }
  /*
    openForgotPasswordModal() {
        const modal = document.getElementById('forgotPasswordModal');
        modal?.classList.remove('hidden'); // esto muestra el modal
    }*/
  isForgotPasswordModalOpen: boolean = false;  // Controla la visibilidad del modal

  openForgotPasswordModal() {
    this.isForgotPasswordModalOpen = true;  // Mostrar el modal
  }

  closeForgotPasswordModal() {
    this.isForgotPasswordModalOpen = false;  // Ocultar el modal
  }

  loginWithOAuth() {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }
  loginWithoutCredentials() {
    this.router.navigate(['../../core/features/dashboard/dashboard']);
  }
  
  get email() {
    return this.loginForm2.controls['email'];
  }
  get password() {
    return this.loginForm2.controls['password'];
  }
}





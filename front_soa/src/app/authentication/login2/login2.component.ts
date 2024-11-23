import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import Swal from 'sweetalert2';
import { AuthService } from '../../core/services/auth.service';


@Component({
  selector: 'app-login2',
  standalone: true,
  imports: [NgIf, ForgotPasswordComponent, RouterModule, ReactiveFormsModule, CommonModule],
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
    const { username, password } = this.loginForm2.value;
    this.authService.loginUser({ username, password }).subscribe(
      response => {
        if (response && response.token) {
          this.authService.token = response.token;
          Swal.fire({
            icon: 'success',
            title: 'Inicio de sesión exitoso',
            text: 'Bienvenido de nuevo'
          });
          this.router.navigate(['/dashboard']); // Redirige al dashboard u otra ruta
        } else {
          console.error('Invalid login response', response);
          Swal.fire({
            icon: 'error',
            title: 'Error al iniciar sesión',
            text: 'Respuesta de inicio de sesión inválida'
          });
        }
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Error al iniciar sesión',
          text: 'Usuario o contraseña incorrectos'
        });
      }
    );
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
    // window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  }
  loginWithoutCredentials() {
    // this.router.navigate(['../../core/features/dashboard/dashboard']);
  }

  get email() {
    return this.loginForm2.controls['email'];
  }
  get password() {
    return this.loginForm2.controls['password'];
  }
}





import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
 
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NgIf,ForgotPasswordComponent],
  templateUrl: './login.component-2.html',
  styles: ``
})
export default class LoginComponent implements OnInit {

  passwordFieldType: string = 'password'; 

  constructor(private route: ActivatedRoute, private router: Router){}

  ngOnInit() {
    //captura el token jwt de la url despues de autenticar
      this.route.queryParams.subscribe( params => {
        const token = params['token'];
        if(token){
          //almacena en el localstorage
          localStorage.setItem('authToken',token);
          //redirige al dashboard
          this.router.navigate(['/dashboard']);
        }
      });
  }
  loginWithOAuth(){
    window.location.href='http://localhost:8080/oauth2/authorization/google';
  }
  loginWithoutCredentials(){
    this.router.navigate(['/dashboard']);
  }

  togglePasswordVisibility() {
    this.passwordFieldType = (this.passwordFieldType === 'password') ? 'text' : 'password';
  }

  registerFromLogin(){
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

}

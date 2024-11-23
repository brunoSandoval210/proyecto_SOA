import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../core/services/user.service';
import { User } from '../../core/models/user.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styles: ``
})
export class RegisterComponent {

  registerForm: FormGroup;

  constructor(
    private userService: UserService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required], // Cambiado de "username" a "name"
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
    
  }

  // Método para registrar un usuario
  registerUser() {
    if (this.registerForm.invalid) {
      alert('Por favor, complete todos los campos correctamente.');
      return;
    }
  
    const userPayload = {
      name: this.registerForm.value.name,
      lastname: this.registerForm.value.lastname,
      email: this.registerForm.value.email,
      password: this.registerForm.value.password
    };
  
    this.userService.createUser(userPayload).subscribe({
      next: (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Usuario registrado',
          text: 'El usuario ha sido registrado exitosamente.'
        });
        this.router.navigate(['/login']);
        this.registerForm.reset();
      },
      error: (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error al registrar',
          text: 'No se pudo registrar el usuario. Por favor, intenta nuevamente.'
        });
      }
    });
  }
}

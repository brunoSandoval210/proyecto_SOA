import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../../core/services/user.service';
import { User } from '../../core/models/user.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styles: ``
})
export class RegisterComponent {

  registerForm: FormGroup;
  user: User | undefined;  // Instancia un nuevo objeto Usuario para enlazarlo con el formulario

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      dni: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  // Método para registrar un usuario
  registerUser() {

    if (this.registerForm.invalid) {
      alert('Por favor, ingrese usuario y contraseña');
      return;
    }

    //this.user = this.registerForm.value;  // Actualiza el objeto user con los valores del formulario

    // Asigna los valores del formulario al objeto `user` con la estructura de `User`
    this.user = this.registerForm.value as User;  // Casting para asignar la estructura de `User`

    this.userService.createUser(this.user).subscribe(
      (response) => {
        console.log('Usuario registrado con éxito:', response);
        this.router.navigate(['/success']);  // Puedes redirigir a una página de éxito si lo deseas
      },
      (error) => {
        console.error('Error al registrar el usuario:', error);
        // Manejo de errores
      }
    );

  }
}

import { Component, Output, EventEmitter, ViewChildren, ElementRef, QueryList } from '@angular/core';
import { NgIf } from '@angular/common';  // Importa CommonModule
import { FormsModule } from '@angular/forms';  // Importa FormsModule para ngModel

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  templateUrl: './forgot-password.component.html',
  imports: [NgIf, FormsModule]  // Agrega los módulos necesarios aquí
})
export class ForgotPasswordComponent {
  @Output() closeModal = new EventEmitter<void>();  // Evento para cerrar el modal
  email: string = '';  // Para almacenar el correo electrónico
  isEmailSent: boolean = false;  // Controla si ya se ha enviado el correo
  isPasswordUpdated: boolean = false;  // Controla si la contraseña fue actualizada

  // Variables para los cuadros de texto del código
  code: string[] = ['', '', '', ''];

  // Usamos ViewChildren para obtener todas las referencias a los inputs
  @ViewChildren('input') inputs!: QueryList<ElementRef>;

  // Enviar el correo
  onSubmitEmail() {
    if (this.email) {
      console.log('Correo enviado a:', this.email);
      this.isEmailSent = true;  // Cambia a la vista del código de verificación
    }
  }

  // Enviar el código de verificación
  onSubmitCode() {
    const fullCode = this.code.join('');  // Unir los valores de los cuadros de texto
    if (fullCode.length === 4) {
      console.log('Código ingresado:', fullCode);
      this.isPasswordUpdated = true;  // Cambiar a la vista de confirmación
    }
    // para quitar el focus
    setTimeout(() => {
      const activeElement = document.activeElement as HTMLElement;
      activeElement?.blur();  // Desenfocar el elemento activo
    }, 0);
  }
  

  // Cambiar el enfoque entre los cuadros de texto
  onInputChange(event: any, index: number) {
    const value = event.target.value;
    if (value.length === 1 && index < 3) {
      // Mover el enfoque al siguiente cuadro si hay un valor y no es el último
      const nextInput = this.inputs.toArray()[index + 1];
      if (nextInput) {
        nextInput.nativeElement.focus();
      }
    }
  }

  // Cerrar el modal
  onClose() {
    this.closeModal.emit();  // Emitimos el evento al hacer clic en Cancelar
  }
}

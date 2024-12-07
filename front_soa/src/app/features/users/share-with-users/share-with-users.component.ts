import { Component } from '@angular/core';
import { GroupService } from '../../../core/services/group.service';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-share-with-users',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './share-with-users.component.html',
  styleUrl: './share-with-users.component.scss',
})
export class ShareWithUsersComponent {
  groupName: string = '';
  selectedUsers: number[] = [];

  email: string = '';
  users: any[] = [];
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private groupService: GroupService,
    private userService: UserService
  ) {}

  createGroup(): void {
    if (this.selectedUsers.length === 0) {
      Swal.fire({
        icon: 'warning',
        title: 'No se puede crear el grupo',
        text: 'Debe seleccionar al menos un usuario para crear el grupo.',
      });
      return;
    }

    const groupData = {
      name: this.groupName,
      usersId: this.selectedUsers,
    };

    this.groupService.createGroup(groupData).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Grupo creado',
          text: `El grupo "${this.groupName}" se ha creado con éxito.`,
        });
        this.groupName = '';
        this.selectedUsers = [];
        this.users = [];
        this.email = '';
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Ocurrió un error al crear el grupo. Por favor, inténtelo de nuevo.',
        });
        console.error('Error al crear el grupo:', error);
      }
    );
  }

  searchUser(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.userService.searchUserByEmail(this.email).subscribe(
      (response) => {
        this.users = response;
        this.isLoading = false;
        if (this.users.length === 0) {
          Swal.fire({
            icon: 'info',
            title: 'Sin resultados',
            text: 'No se encontraron usuarios con el correo ingresado.',
          });
        }
      },
      (error) => {
        this.errorMessage = 'Error al buscar usuarios.';
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: this.errorMessage,
        });
        console.error('Error:', error);
        this.isLoading = false;
      }
    );
  }

  toggleUserSelection(userId: number): void {
    if (this.selectedUsers.includes(userId)) {
      this.selectedUsers = this.selectedUsers.filter((id) => id !== userId);
    } else {
      this.selectedUsers.push(userId);
    }
  }
}

import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ColumnComponent } from '../column/column.component';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule, ColumnComponent],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent {
  @Input() table: { id: number; name: string; columns: any[] } | null = null;

  
}

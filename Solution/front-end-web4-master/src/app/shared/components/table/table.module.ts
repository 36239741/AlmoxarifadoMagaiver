import { MatIconModule } from '@angular/material/icon';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table.component';
import { CovalentDataTableModule } from '@covalent/core/data-table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MAT_LABEL_GLOBAL_OPTIONS } from '@angular/material/core';

@NgModule({
  imports: [
    CommonModule,
    CovalentDataTableModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule
  ],
  declarations: [TableComponent],
  exports: [TableComponent],
  providers: [{provide: MAT_LABEL_GLOBAL_OPTIONS, useValue: {float: 'never'}}]
})
export class TableModule { }

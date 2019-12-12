import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchComponent } from './search.component';
import { CovalentSearchModule } from '@covalent/core/search';


@NgModule({
  imports: [
    CommonModule,
    CovalentSearchModule
  ],
  declarations: [SearchComponent],
  exports: [SearchComponent]
})
export class SearchModule { }

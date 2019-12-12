import { Routes, RouterModule } from '@angular/router';
import { ScreenComponent } from './screen/screen.component';
import { BlockFloatComponent } from './block-float/block-float.component';
import { NgModule } from '@angular/core';



const routes: Routes = [
  { path: '' , component: ScreenComponent}
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

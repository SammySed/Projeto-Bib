import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadLeitorComponent } from './cad-leitor/cad-leitor.component';
import { HomeComponent } from './home/home.component';
import { LeitorComponent } from './leitor/leitor.component';

const routes: Routes = [

  {path:"", component: HomeComponent},
  {path: "leitores", component: CadLeitorComponent},
  {path: "leitor/:id", component: LeitorComponent},
  {path: "leitor", component: LeitorComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

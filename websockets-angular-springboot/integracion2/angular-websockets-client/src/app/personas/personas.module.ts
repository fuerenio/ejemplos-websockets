import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PersonasService} from '../services/personas.service';
import { FormsModule } from '@angular/forms';
import { AltaPersonasComponent } from '../components/alta-personas/alta-personas.component';
import { ListadoPersonasComponent } from '../components/listado-personas/listado-personas.component';

@NgModule({
  declarations: [
      AltaPersonasComponent,
      ListadoPersonasComponent,
    ],
  imports: [
    CommonModule, FormsModule
  ],
  providers: [
    PersonasService
  ],
  exports: [
    AltaPersonasComponent, ListadoPersonasComponent
  ]
})
export class PersonasModule { }

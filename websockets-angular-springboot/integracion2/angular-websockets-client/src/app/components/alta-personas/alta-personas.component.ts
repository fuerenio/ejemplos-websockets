
import { Component, Input, OnInit } from '@angular/core';
import { PersonasService } from '../../services/personas.service';
import { Persona, Sexo } from '../../models/persona';

@Component({
  selector: 'app-alta-personas',
  templateUrl: './alta-personas.component.html',
  styleUrls: ['./alta-personas.component.css']
})
export class AltaPersonasComponent implements OnInit {

  @Input() persona: Persona;
  generosPersona: Sexo[];

  constructor(private personasService: PersonasService) { }

  ngOnInit() {
    this.persona = this.personasService.newPerson();
    this.generosPersona = this.personasService.getGeneros();
  }

  addPerson() {
    console.log(this.persona);
    this.personasService.save(this.persona);
    this.persona = this.personasService.newPerson();
  }

}

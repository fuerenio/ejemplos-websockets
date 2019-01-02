import { Component, OnInit } from '@angular/core';
import { PersonasService } from '../../services/personas.service';
import { Persona, Sexo } from '../../models/persona';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-listado-personas',
  templateUrl: './listado-personas.component.html',
  styleUrls: ['./listado-personas.component.css']
})
export class ListadoPersonasComponent implements OnInit {

  personas: Persona[] = [];
  personas$: Observable<Persona[]>;

  constructor(private personasService: PersonasService) {

   }

  ngOnInit() {
    this.personas$ = this.personasService.getPersons$();
    this.personas$.subscribe(personas => this.personas = personas);

    this.personasService.getPersons().then((personas) => {
      this.personas = personas;
    });

  }

}

import { Injectable } from '@angular/core';
import { TestDaoService } from './test-dao.service';
import { Persona } from '../models/persona';
import { Sexo } from '../models/persona';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs';
import { RxStompService} from '@stomp/ng2-stompjs';

@Injectable({
  providedIn: 'root'
})
export class PersonasService {

  private generosPersona: Sexo[];
  private personas$: Subject<Persona[]> = new Subject<Persona[]>();

  constructor(private testDaoService: TestDaoService, private rxStompService: RxStompService) {
    this.generosPersona = [
      {id: -1, descripcion: 'Seleccione'},
      {id: 1, descripcion: 'Femenino'},
      {id: 2, descripcion: 'Masculino'}
    ];
  }
  getPersons$(): Observable<Persona[]> {
    return this.personas$.asObservable();
  }

  getGeneros(): Sexo[] {
    return this.generosPersona;
  }

  getPersons(): Promise<Persona[]> {
    return this.testDaoService.getAll();
  }

  save(persona: Persona): Promise<any> {
    return this.testDaoService.save(persona).then((personaGuardada) => {
      console.log('PersonasService > Se realizara la propagacion de la persona...');
      console.log(personaGuardada.value);
      this.rxStompService.publish({destination: '/app/message', body: JSON.stringify(personaGuardada.value)});

      this.getPersons().then((personass) => {
        this.personas$.next(personass);
      });
    });
  }

  newPerson(): Persona {
    let persona: Persona;
    persona = {
      nombre: '',
      apellidos: '',
      email: '',
      edad: 0,
      direccion: '',
      sexo: -1,
      registerDate: new Date()
    };

    return persona;
  }
}

import { Injectable } from '@angular/core';
import { AngularIndexedDB } from 'angular2-indexeddb';
import { Persona } from '../models/persona';

@Injectable({
  providedIn: 'root'
})
export class TestDaoService {
  DB_NAME = 'AppTestDb';
  STORE_NAME = 'personas';
  daoIndexedDB: AngularIndexedDB;

  constructor() {
    console.log('Ejecutando TestDaoService.constructor()...');
    this.daoIndexedDB = new AngularIndexedDB(this.DB_NAME, 1);
      this.daoIndexedDB.openDatabase(1, (evt) => {
        const objectStore = evt.currentTarget.result.createObjectStore(
          this.STORE_NAME, { keyPath: 'personId', autoIncrement: true }
        );
        objectStore.createIndex('personId', 'personId', { unique: true });
        objectStore.createIndex('email', 'email', { unique: true });
      });
  }


  getAll(): Promise<Persona[]> {
    console.log('Ejecutando TestDaoService.getAll()...');
    return this.daoIndexedDB.openDatabase(1, (evt) => {}).then(dbOpened => {
      return this.daoIndexedDB.getAll(this.STORE_NAME).then((personas) => {
        return Promise.resolve(personas);
      }).catch(this.handleError);
    });

  }

  findById(personId: string ): Promise<Persona> {
    console.log('Ejecutando TestDaoService.findById()...');
      return this.daoIndexedDB.getByKey(this.STORE_NAME, personId).then((persona) => {
        console.log(persona);
        return persona;
      }).catch(this.handleError);
  }

  save(personData: Persona): Promise<any> {
    console.log('Ejecutando TestDaoService.save()...');
    return this.daoIndexedDB.add(this.STORE_NAME, personData).then((response) => {
      console.log(response);
      return Promise.resolve(response);
    }).catch(this.handleError);
  }

  private handleError(error: any) {
    console.error('Ocurrio un error', error);
    return Promise.reject(error.message || error);
  }
}

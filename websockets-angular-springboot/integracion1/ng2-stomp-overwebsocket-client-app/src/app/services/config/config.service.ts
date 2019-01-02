import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { StompConfig } from '../stomp';

/**
 * An injected class which grabs the application
 * config variables (e.g. STOMP credentials)
 * for the user application.
 *
 * This makes an AJAX request to the server
 * api containing some user token and secret
 *
 * @type ConfigService
 */
@Injectable()
export class ConfigService {

  // TODO: Provide a user object to the constructor
  //       to allow retrieval of per-user configs
  //       or from a specific URL.
  /** Constructor */
  constructor(private _http: Http) { }


  /** Make an http request for a config file, and
    * return a Promise for its resolution.
    */
  public getConfig(path): Promise<StompConfig> {
    /*
    return this._http.get(path)
      .map(res => res.json())
      .toPromise();
    */
   // config hardcode
    const cgf = {
      host: 'localhost',
      port: 8080,
      https: false,
      user: 'myusername',
      pass: 'mypassword',

      // user: 'notaspro',
      // pass: '$2a$10$90Z9RByPD66H3N1cy09W/OUnG2gTkeawcOvfMV/HpPUOt5bN7fJV.',

      subscribe: ['/topic/testSubscribe'],
      publish: ['/topic/testSubscribe'],

      heartbeat_in: 0,
      heartbeat_out: 10000,
      ssl: false,
      debug: true
    };
    return Promise.resolve(cgf);
  }
}

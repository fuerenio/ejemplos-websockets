/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { PersonaDto } from '../models/persona-dto';

/**
 * Websocket Controller
 */
@Injectable({
  providedIn: 'root',
})
class WebsocketControllerService extends __BaseService {
  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `WebsocketControllerService.BroadcastStudentUsingPOSTParams` containing the following parameters:
   *
   * - `personaDto`: personaDto
   *
   * - `Authorization`: notaspro token
   *
   * @return OK
   */
  broadcastStudentUsingPOSTResponse(params: WebsocketControllerService.BroadcastStudentUsingPOSTParams): __Observable<__StrictHttpResponse<boolean>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.personaDto;
    if (params.Authorization != null) __headers = __headers.set('Authorization', params.Authorization.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/websocket/broadcast`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'text'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return (_r as HttpResponse<any>).clone({ body: (_r as HttpResponse<any>).body === 'true' }) as __StrictHttpResponse<boolean>
      })
    );
  }
  /**
   * @param params The `WebsocketControllerService.BroadcastStudentUsingPOSTParams` containing the following parameters:
   *
   * - `personaDto`: personaDto
   *
   * - `Authorization`: notaspro token
   *
   * @return OK
   */
  broadcastStudentUsingPOST(params: WebsocketControllerService.BroadcastStudentUsingPOSTParams): __Observable<boolean> {
    return this.broadcastStudentUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as boolean)
    );
  }
}

module WebsocketControllerService {

  /**
   * Parameters for broadcastStudentUsingPOST
   */
  export interface BroadcastStudentUsingPOSTParams {

    /**
     * personaDto
     */
    personaDto: PersonaDto;

    /**
     * notaspro token
     */
    Authorization?: string;
  }
}

export { WebsocketControllerService }

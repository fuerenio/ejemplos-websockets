/* tslint:disable */
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ApiConfiguration } from './api-configuration';

import { PersonaControllerService } from './services/persona-controller.service';
import { WebsocketControllerService } from './services/websocket-controller.service';

/**
 * Provider for all Api services, plus ApiConfiguration
 */
@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [
    ApiConfiguration,
    PersonaControllerService,
    WebsocketControllerService
  ],
})
export class ApiModule { }

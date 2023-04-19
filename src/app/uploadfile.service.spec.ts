import { TestBed } from '@angular/core/testing';

import { UploadfileService } from './uploadfile.service';

describe('UploadfileService', () => {
  let service: UploadfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadfileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

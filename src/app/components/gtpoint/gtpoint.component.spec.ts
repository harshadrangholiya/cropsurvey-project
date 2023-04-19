import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GtpointComponent } from './gtpoint.component';

describe('GtpointComponent', () => {
  let component: GtpointComponent;
  let fixture: ComponentFixture<GtpointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GtpointComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GtpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

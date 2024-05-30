import {browser} from 'k6/experimental/browser';
import { check,sleep } from 'k6';

export const options = {
    scenarios: {
      ui: {
        executor: 'shared-iterations', // para realizar iteraciones sin indicar el tiempo
        options: {
          browser: {
            type: 'chromium',
          },
        },
      } 
    },
    thresholds: {
      checks: ["rate==1.0"]
    }
  }
  
  export default async function () {
    const page = browser.newPage();
    try {
      await page.goto('http://localhost:4200/');

      page.locator('input[name="nombre"]').type('Dr. Juan Pérez');
      page.locator('input[name="DNI"]').type('12345678A');

      const submitButtonLogin = page.locator('button[name="login"]');
      await Promise.all([page.waitForNavigation({waitUntil:'networkidle'}), submitButtonLogin.click()]);

      sleep(5);
      check(page, {
        'Redirected to Patient Page': (p) => p.url() === 'http://localhost:4200/home',
      });

      const submitButtonAdd = page.locator('button[name="add"]');
      await Promise.all([page.waitForNavigation({waitUntil:'networkidle'}), submitButtonAdd.click()]);

      sleep(5);
      check(page, {
        'Redirected to Create Patient Page': (p) => p.url() === 'http://localhost:4200/paciente/create',
      });

      page.locator('input[name="dni"]').type('12345678B');
      page.locator('input[name="nombre"]').type('Juan Alberto');
      page.locator('input[name="edad"]').type(30);
      page.locator('input[name="cita"]').type('manana');

      const submitButtonCreate = page.locator('button[type="submit"]');
      await Promise.all([page.waitForNavigation(), submitButtonCreate.click()]);
      
      check(page, {
        'Redirected to Home Page': (p) => p.url() === 'http://localhost:4200/home',
      });

    } finally {
      page.close();
    }
  }
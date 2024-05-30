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

      sleep(1);
      check(page, {
        'Redirected to Home Page': (p) => p.url() === 'http://localhost:4200/home',
      });


      // Localizar la fila deseada (por ejemplo, la primera fila)
      const firstPatientRow = page.locator('td[name="nombre"]')
      await Promise.all([page.waitForNavigation(), firstPatientRow.click()]);

      sleep(1);
      check(page, {
        'Redirected to Patient Page': (p) => p.url() === 'http://localhost:4200/paciente/1',
      });

      const EyeIcon = page.locator('button[name="view"]')
      await Promise.all([page.waitForNavigation(), EyeIcon.click()]);

      sleep(1);
      check(page, {
        'Redirected to Image Page': (p) => p.url() === 'http://localhost:4200/image-detail/1',
      });

    const Predict = page.locator('button[name="predict"]')
    await Promise.all([Predict.click()]);
    

      sleep(1);
      const predictionText = await page.locator('div.center-content.result.ng-star-inserted').innerText();
    check(page, {
      'Prediction Text is Correct': (p) => predictionText.includes('Probabilidad de cáncer: Not cancer (label 0), score: 0.984481368213892'),
    });

    } finally {
      page.close();
    }
  }
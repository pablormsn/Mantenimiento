import http from 'k6/http';
import { check } from 'k6';

export let options = {
  stages: [
    { duration: '10m', target: 100000 }, // ramp up to 100000 users over 10 minutes
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'], // <1% errors
  },
};

export default function () {
  let res = http.get('http://localhost:8080/medico/1');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}

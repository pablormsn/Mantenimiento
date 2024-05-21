import http from 'k6/http';
import { check } from 'k6';

export let options = {
  stages: [
    { duration: '3m', target: 2527 },
    { duration: '3m', target: 2527 },
    { duration: '2m', target: 0 },
  ],
  thresholds: {
    http_req_failed: ['rate<0.01'],
  },
};

export default function () {
  let res = http.get('http://localhost:8080/medico/1');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}

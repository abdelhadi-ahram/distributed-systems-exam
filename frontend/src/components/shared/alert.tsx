import { InformationCircleIcon, CheckCircleIcon } from "@heroicons/react/24/outline";

import type { ServerActionResponse } from '@/lib/types/server-action-response';
import { Alert, AlertDescription } from '@/components/ui/alert';

export default function ResponseAlert({ data }: { data: ServerActionResponse | null }) {
  const messages = data?.message ? data.message.split('\n') : []

  return data ? (
    <Alert variant={data.success ? 'success' : 'destructive'} className='mb-6'>
      {data.success ? <CheckCircleIcon className="h-4 w-4" /> : <InformationCircleIcon className="h-4 w-4" />}
      <AlertDescription>
        {messages.map((item, key) => (
          <p key={key}>{item}</p>
        ))}
      </AlertDescription>
    </Alert>
  ) : <></>
}
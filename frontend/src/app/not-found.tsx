
import { Button } from "@/components/ui/button"
import Link from "next/link"

export default function Component() {
  return (
    <div className="flex flex-col items-center justify-center min-h-[100dvh] bg-background-light p-4">
      <div className="max-w-md w-full space-y-6 text-center">
        <div className="space-y-2">
          <h1 className="text-4xl font-bold">Oops! Page not found.</h1>
          <p className="">The page you&apos;re looking for doesn&apos;t exist or has been moved.</p>
        </div>
        <Button asChild variant="secondary">
          <Link
            href="/"
            prefetch={false}
          >
            Go back home
          </Link>
        </Button>
      </div>
    </div>
  )
}